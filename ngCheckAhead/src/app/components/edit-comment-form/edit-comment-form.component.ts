import { Component, OnInit, Input } from '@angular/core';
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

  constructor(
    private router: Router,
    private svc: ReviewCommentService
  ) { }

  ngOnInit(): void {

  }

  postComment(){
    this.svc.update(this.editComment).subscribe(
      data => {
        console.log('CommentComponent.create(): comment created.');
        this.router.navigateByUrl('/locations/'+ this.editComment.review.location.id)
      },
      err => {
        console.error('CommentComponent.create(): ERROR.');
        console.error(err);
      }
    );
  }
}
