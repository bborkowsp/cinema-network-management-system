import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-repertory',
  templateUrl: './repertory.component.html',
  styleUrls: ['./repertory.component.scss']
})
export class RepertoryComponent implements OnInit {

  ngOnInit(): void {
    console.log('RepertoryComponent initialized');
  }
}
