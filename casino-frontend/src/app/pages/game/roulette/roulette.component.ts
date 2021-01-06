import {Component, OnInit} from '@angular/core';
import {DataService} from "../../../services/data.service";
import {ActivatedRoute} from "@angular/router";
import {RouletteChoice, RouletteResponseDto} from "../../../model/game";
import {GameService} from "../../../services/game.service";

@Component({
  selector: 'app-roulette',
  templateUrl: './roulette.component.html',
  styleUrls: ['./roulette.component.scss']
})
export class RouletteComponent implements OnInit {

  rouletteChoice = RouletteChoice;

  roulette: RouletteResponseDto;
  numbers = new Array(36);
  guessSingle: number;
  guessRedOrBlack: string;
  guessEvenOrOdd: string;


  constructor(private data: DataService,
              private game: GameService,
              private route: ActivatedRoute) {
    this.roulette = this.route.snapshot.data.roulette;
  }

  ngOnInit(): void {
  }

  roll(rouletteChoice: RouletteChoice, value: any) {
    this.game.postRoulette({choice: rouletteChoice, value: value.toString()})
      .then(response => this.roulette = response);
  }
}
