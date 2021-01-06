import {Component, OnInit} from '@angular/core';
import {DataService} from "../../../services/data.service";
import {CasinoUserDto} from "../../../model/data";
import {GameConnectionService} from "../../../services/connection/game-connection.service";
import {SlotsDto} from "../../../model/game";
import {ActivatedRoute} from "@angular/router";
import {GameService} from "../../../services/game.service";

@Component({
  selector: 'app-slots',
  templateUrl: './slots.component.html',
  styleUrls: ['./slots.component.scss']
})
export class SlotsComponent implements OnInit {

  slots: SlotsDto;

  constructor(private data: DataService,
              private game: GameService,
              private route: ActivatedRoute) {
    this.slots = this.route.snapshot.data.slots;
  }

  ngOnInit(): void {

  }

  roll() {
    this.game.postSlots().then(res => this.slots = res);
  }
}
