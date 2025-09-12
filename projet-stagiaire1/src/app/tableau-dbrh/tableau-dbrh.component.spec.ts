import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableauDBrhComponent } from './tableau-dbrh.component';

describe('TableauDBrhComponent', () => {
  let component: TableauDBrhComponent;
  let fixture: ComponentFixture<TableauDBrhComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TableauDBrhComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TableauDBrhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
