import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ReviewCommentService } from 'src/app/services/review-comment.service';
import { Comment } from './../../models/comment';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.css']
})
export class CommentFormComponent implements OnInit {

  @Input() newComment: Comment;
  @Output() commentAdded = new EventEmitter<string>();

  constructor(
    private router: Router,
    private svc: ReviewCommentService
  ) { }

  ngOnInit(): void {

  }

  reload() {
    this.commentAdded.emit('');
  }

  postComment(){
    this.svc.create(this.newComment).subscribe(
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
