import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessagerieStagiaireComponent } from './messagerie-stagiaire.component';

describe('MessagerieStagiaireComponent', () => {
  let component: MessagerieStagiaireComponent;
  let fixture: ComponentFixture<MessagerieStagiaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MessagerieStagiaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MessagerieStagiaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
