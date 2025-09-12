import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesProjetsStagiaireComponent } from './mes-projets-stagiaire.component';

describe('MesProjetsStagiaireComponent', () => {
  let component: MesProjetsStagiaireComponent;
  let fixture: ComponentFixture<MesProjetsStagiaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MesProjetsStagiaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MesProjetsStagiaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
