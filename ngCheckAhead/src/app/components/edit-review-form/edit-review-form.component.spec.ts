import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditReviewFormComponent } from './edit-review-form.component';

describe('EditReviewFormComponent', () => {
  let component: EditReviewFormComponent;
  let fixture: ComponentFixture<EditReviewFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditReviewFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditReviewFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
