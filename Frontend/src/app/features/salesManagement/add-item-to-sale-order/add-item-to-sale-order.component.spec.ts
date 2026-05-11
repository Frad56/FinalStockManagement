import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddItemToSaleOrderComponent } from './add-item-to-sale-order.component';

describe('AddItemToSaleOrderComponent', () => {
  let component: AddItemToSaleOrderComponent;
  let fixture: ComponentFixture<AddItemToSaleOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddItemToSaleOrderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddItemToSaleOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
