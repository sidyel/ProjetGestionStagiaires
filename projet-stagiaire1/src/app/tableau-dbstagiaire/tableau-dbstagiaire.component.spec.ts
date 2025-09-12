import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableauDBStagiaireComponent } from './tableau-dbstagiaire.component';

describe('TableauDBStagiaireComponent', () => {
  let component: TableauDBStagiaireComponent;
  let fixture: ComponentFixture<TableauDBStagiaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TableauDBStagiaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TableauDBStagiaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
