import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {CasinoUserDto} from "../../model/data";
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  casinoUser?: CasinoUserDto;

  constructor(private tokenStorage: TokenStorageService,
              private data: DataService) { }

  ngOnInit(): void {
    this.data.getCasinoUser(this.tokenStorage.getUser().username)
      .then(response => this.casinoUser = response as CasinoUserDto);
  }

  getNickname() {
    return this.tokenStorage.getUser().username;
  }
}
