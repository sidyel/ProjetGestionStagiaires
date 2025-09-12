import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbsenceStagiaireComponent } from './absence-stagiaire.component';

describe('AbsenceStagiaireComponent', () => {
  let component: AbsenceStagiaireComponent;
  let fixture: ComponentFixture<AbsenceStagiaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AbsenceStagiaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AbsenceStagiaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
