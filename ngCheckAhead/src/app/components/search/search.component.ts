import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchKey: string = null;

  constructor() { }

  ngOnInit(): void {

  }

  searchByKeyword(){
    this.searchKey = this.searchByKeyword;
  }

}
