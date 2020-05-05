import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLocationFormComponent } from './edit-location-form.component';

describe('EditLocationFormComponent', () => {
  let component: EditLocationFormComponent;
  let fixture: ComponentFixture<EditLocationFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditLocationFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditLocationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
