package com.example.store.service.stockManagment.implementation;

import com.example.store.dto.stockManagment.CharacteristicValueDTO;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.model.stockManagement.Characteristic;
import com.example.store.model.stockManagement.CharacteristicValue;

import com.example.store.model.stockManagement.Product;
import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.repository.stockManagement.CharacteristicRepository;
import com.example.store.repository.stockManagement.CharacteristicValueRepository;
import com.example.store.service.stockManagment.interfaces.CharacteristicValueService;
import com.example.store.service.stockManagment.interfaces.ProductCharacteristicService;
import com.example.store.service.stockManagment.interfaces.ProductVariantService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class CharacteristicValueServiceImpl implements CharacteristicValueService {


    private final CharacteristicValueRepository characteristicValueRepository;
    private final ProductCharacteristicService productCharacteristicService;
    private final ProductVariantService productVariantService;
    private final CharacteristicRepository characteristicRepository;

    public CharacteristicValueServiceImpl(CharacteristicValueRepository characteristicValueRepository,
                                          ProductCharacteristicService productCharacteristicService,
                                          ProductVariantService productVariantService,
                                          CharacteristicRepository characteristicRepository){
        this.characteristicValueRepository=characteristicValueRepository;
            this.productCharacteristicService=productCharacteristicService;
            this.productVariantService=productVariantService;
            this.characteristicRepository=characteristicRepository;
    }


    private void mapDTOToCharacteristicValue(CharacteristicValueDTO characteristicValueDTO,
                                             CharacteristicValue characteristicValue){

        Characteristic characteristic = characteristicRepository.findById(characteristicValueDTO.getCharacteristicId())
                .orElseThrow(() -> new ElementNotFoundException(characteristicValueDTO.getCharacteristicId()));


        characteristicValue.setCharacteristic(characteristic);

        characteristicValue.setProductVariant(productVariantService.findProductVariantById
                (characteristicValueDTO.getProductVariantId()));

        characteristicValue.setValue(characteristicValueDTO.getValue());
    }



    public String generateVariantCodeFromValues(Product product, List<CharacteristicValue> values) {
        String base = product.getReference();

        String suffix = values.stream()
                .map(cv -> cv.getCharacteristic().getName().substring(0, Math.min(3, cv.getCharacteristic().getName().length())).toUpperCase()
                        + "-"
                        + cv.getValue().substring(0, Math.min(3, cv.getValue().length())).toUpperCase())
                .collect(Collectors.joining("-"));

        return base + "-" + suffix;
    }

    @Override
    public CharacteristicValue saveCharacteristicValue(CharacteristicValueDTO characteristicValueDTO) {
        CharacteristicValue characteristicValueDB = new CharacteristicValue();
        mapDTOToCharacteristicValue(characteristicValueDTO, characteristicValueDB);

        CharacteristicValue saved = characteristicValueRepository.save(characteristicValueDB);

        ProductVariant pv = saved.getProductVariant();
        List<CharacteristicValue> allValues =
                characteristicValueRepository.findByProductVariant_ProductVariantId(pv.getProductVariantId());

        String code = generateVariantCodeFromValues(pv.getProduct(), allValues);

        pv.setCode(code);
        productVariantService.updateProductVariant(pv, pv.getProductVariantId());

        return saved;
    }

    @Override
    public CharacteristicValue findCharacteristicValueById(Long characteristicValueId){
        return characteristicValueRepository.findById(characteristicValueId).orElseThrow(()->
                new ElementNotFoundException(characteristicValueId));
    }

    @Override
    public List<CharacteristicValue> fetchCharacteristicValueList(){
        return characteristicValueRepository.findAll();
    }


    @Override
    public CharacteristicValue updateCharacteristicValue(CharacteristicValueDTO characteristicValueDTO, Long characteristicValueId) {
        CharacteristicValue characteristicValueDB = findCharacteristicValueById(characteristicValueId);
        mapDTOToCharacteristicValue(characteristicValueDTO, characteristicValueDB);
        CharacteristicValue saved = characteristicValueRepository.save(characteristicValueDB);

        ProductVariant pv = saved.getProductVariant();
        List<CharacteristicValue> allValues =
                characteristicValueRepository.findByProductVariant_ProductVariantId(pv.getProductVariantId());
        String code = generateVariantCodeFromValues(pv.getProduct(), allValues);
        pv.setCode(code);
        productVariantService.updateProductVariant(pv, pv.getProductVariantId());

        return saved;
    }
    @Override
    public void deleteCharacteristicValueById(Long characteristicValueId){
       if(!characteristicValueRepository.existsById(characteristicValueId)){
           throw new ElementNotFoundException(characteristicValueId);
       }
       characteristicValueRepository.deleteById(characteristicValueId);
    }

    @Override
    public List<CharacteristicValue> saveAll(List<CharacteristicValueDTO> characteristicValues) {
        List<CharacteristicValue> savedList = new ArrayList<>();

        for (CharacteristicValueDTO dto : characteristicValues) {
            CharacteristicValue cv = new CharacteristicValue();
            mapDTOToCharacteristicValue(dto, cv);
            savedList.add(characteristicValueRepository.save(cv));
        }

        savedList.stream()
                .collect(Collectors.groupingBy(cv -> cv.getProductVariant().getProductVariantId()))
                .forEach((pvId, cvList) -> {
                    ProductVariant pv = cvList.get(0).getProductVariant();
                    List<CharacteristicValue> allValues =
                            characteristicValueRepository.findByProductVariant_ProductVariantId(pvId);
                    String code = generateVariantCodeFromValues(pv.getProduct(), allValues);
                    pv.setCode(code);
                    productVariantService.updateProductVariant(pv, pvId);
                });

        return savedList;
    }



    @Override
    public Map<String, String> findCharacteristicValueByProductVariantId(Long productVariantId) {

        productVariantService.findProductVariantById(productVariantId);

        List<CharacteristicValue> characteristicValueList =
                characteristicValueRepository.findByProductVariant_ProductVariantId(productVariantId);

        return characteristicValueList.stream()
                .collect(Collectors.toMap(
                        cv -> cv.getCharacteristic().getName() ,
                        cv ->  cv.getValue()
                ));
    }

    public Map<String, String> UpdateCharacteristicValueByProductVariantId(Long productVariantId) {

        productVariantService.findProductVariantById(productVariantId);

        List<CharacteristicValue> characteristicValueList =
                characteristicValueRepository.findByProductVariant_ProductVariantId(productVariantId);

        return characteristicValueList.stream()
                .collect(Collectors.toMap(
                        cv -> cv.getCharacteristic().getName() ,
                        cv ->  cv.getValue()
                ));
    }

    @Override
    public void updateCharacteristicValues(Long productVariantId, Map<String, String> newValues) {
        List<CharacteristicValue> list =
                characteristicValueRepository.findByProductVariant_ProductVariantId(productVariantId);

        for (CharacteristicValue cv : list) {

            String name = cv.getCharacteristic().getName();

            if (newValues.containsKey(name)) {
                cv.setValue(newValues.get(name));
            }else{
               throw new ElementNotFoundException("Characteristic with name " + name + " not found for product variant with id " + productVariantId);
            }
        }

        characteristicValueRepository.saveAll(list);
    }

}


