import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignStagiairesComponent } from './assign-stagiaires.component';

describe('AssignStagiairesComponent', () => {
  let component: AssignStagiairesComponent;
  let fixture: ComponentFixture<AssignStagiairesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssignStagiairesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AssignStagiairesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
