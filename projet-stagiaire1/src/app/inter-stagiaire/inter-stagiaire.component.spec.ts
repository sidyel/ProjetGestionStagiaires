import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterStagiaireComponent } from './inter-stagiaire.component';

describe('InterStagiaireComponent', () => {
  let component: InterStagiaireComponent;
  let fixture: ComponentFixture<InterStagiaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InterStagiaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InterStagiaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
