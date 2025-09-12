import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesTacheComponent } from './mes-tache.component';

describe('MesTacheComponent', () => {
  let component: MesTacheComponent;
  let fixture: ComponentFixture<MesTacheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MesTacheComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MesTacheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
