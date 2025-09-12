import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterEncadrantComponent } from './inter-encadrant.component';

describe('InterEncadrantComponent', () => {
  let component: InterEncadrantComponent;
  let fixture: ComponentFixture<InterEncadrantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InterEncadrantComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InterEncadrantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
