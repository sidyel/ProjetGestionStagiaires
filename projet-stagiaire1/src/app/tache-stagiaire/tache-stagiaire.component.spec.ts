import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TacheStagiaireComponent } from './tache-stagiaire.component';

describe('TacheStagiaireComponent', () => {
  let component: TacheStagiaireComponent;
  let fixture: ComponentFixture<TacheStagiaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TacheStagiaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TacheStagiaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
