import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovementInStockEditComponent } from './movement-in-stock-edit.component';

describe('MovementInStockEditComponent', () => {
  let component: MovementInStockEditComponent;
  let fixture: ComponentFixture<MovementInStockEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MovementInStockEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MovementInStockEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
