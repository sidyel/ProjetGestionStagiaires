import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableauDBEncadrantComponent } from './tableau-dbencadrant.component';

describe('TableauDBEncadrantComponent', () => {
  let component: TableauDBEncadrantComponent;
  let fixture: ComponentFixture<TableauDBEncadrantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TableauDBEncadrantComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TableauDBEncadrantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
