import { Comment } from './../../models/comment';
import { Component, OnInit, Input } from '@angular/core';
import { ReviewCommentService } from 'src/app/services/review-comment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.css']
})
export class CommentFormComponent implements OnInit {

  @Input() newComment: Comment;

  constructor(
    private router: Router,
    private svc: ReviewCommentService
  ) { }

  ngOnInit(): void {

  }

  postComment(){
    this.svc.create(this.newComment).subscribe(
      data => {
        console.log('CommentComponent.create(): comment created.');
        this.router.navigateByUrl('/locations/'+ this.newComment.review.location.id)
      },
      err => {
        console.error('CommentComponent.create(): ERROR.');
        console.error(err);
      }
    );
  }


}
