import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GiveNoteComponent } from './give-note.component';

describe('GiveNoteComponent', () => {
  let component: GiveNoteComponent;
  let fixture: ComponentFixture<GiveNoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GiveNoteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GiveNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
