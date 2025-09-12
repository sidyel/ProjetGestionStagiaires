import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesStagiaireComponent } from './mes-stagiaire.component';

describe('MesStagiaireComponent', () => {
  let component: MesStagiaireComponent;
  let fixture: ComponentFixture<MesStagiaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MesStagiaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MesStagiaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
