import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovementInStockCreateComponent } from './movement-in-stock-create.component';

describe('MovementInStockCreateComponent', () => {
  let component: MovementInStockCreateComponent;
  let fixture: ComponentFixture<MovementInStockCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovementInStockCreateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MovementInStockCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
