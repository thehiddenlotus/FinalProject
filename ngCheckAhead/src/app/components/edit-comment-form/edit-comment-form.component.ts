import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ReviewCommentService } from 'src/app/services/review-comment.service';
import { Router } from '@angular/router';
import { Comment } from 'src/app/models/comment';

@Component({
  selector: 'app-edit-comment-form',
  templateUrl: './edit-comment-form.component.html',
  styleUrls: ['./edit-comment-form.component.css']
})
export class EditCommentFormComponent implements OnInit {

  @Input() editComment: Comment;
  @Output() commentUpdated = new EventEmitter<string>();

  constructor(
    private router: Router,
    private svc: ReviewCommentService
  ) { }

  ngOnInit(): void {

  }

  reload() {
    this.commentUpdated.emit('');
  }

  postComment(){
    this.svc.update(this.editComment).subscribe(
      data => {
        console.log('CommentComponent.create(): comment created.');
        this.reload();
      },
      err => {
        console.error('CommentComponent.create(): ERROR.');
        console.error(err);
      }
    );
  }
}
