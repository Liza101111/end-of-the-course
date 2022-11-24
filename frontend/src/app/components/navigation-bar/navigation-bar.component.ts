import { Component, OnInit } from '@angular/core';
import {expenseListUrl, homeUrl, incomeListUrl} from "../../models/urls";


@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  home = homeUrl
  incomes = incomeListUrl
  expenses = expenseListUrl



  constructor() { }

  ngOnInit(): void {
  }

}
