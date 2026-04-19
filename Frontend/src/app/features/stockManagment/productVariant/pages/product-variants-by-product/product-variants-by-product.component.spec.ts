import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductVariantsByProductComponent } from './product-variants-by-product.component';

describe('ProductVariantsByProductComponent', () => {
  let component: ProductVariantsByProductComponent;
  let fixture: ComponentFixture<ProductVariantsByProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductVariantsByProductComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProductVariantsByProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
