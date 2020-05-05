import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCommentFormComponent } from './edit-comment-form.component';

describe('EditCommentFormComponent', () => {
  let component: EditCommentFormComponent;
  let fixture: ComponentFixture<EditCommentFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCommentFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCommentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
