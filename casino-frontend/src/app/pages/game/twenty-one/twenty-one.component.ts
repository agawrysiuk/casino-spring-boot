import {Component, OnInit} from '@angular/core';
import {Card, TwentyOneRequestCode, TwentyOneResponseDto} from "../../../model/game";
import {GameService} from "../../../services/game.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-twenty-one',
  templateUrl: './twenty-one.component.html',
  styleUrls: ['./twenty-one.component.scss']
})
export class TwentyOneComponent implements OnInit {

  requestCode = TwentyOneRequestCode;
  twentyOne: TwentyOneResponseDto;

  constructor(private game: GameService,
              private route: ActivatedRoute) {
    this.twentyOne = this.route.snapshot.data.twentyOne;
  }

  ngOnInit(): void {
  }

  getCardImage(dealer: boolean, index: number) {
    const hand: Card[] = dealer? this.twentyOne.dealersCards : this.twentyOne.yourCards;
    return "assets/images/cards/"
      + hand[index].cardStrength.split("")[0]
      + hand[index].cardColor.split("")[0]
      + ".png";
  }

  sendRequest(requestCode: TwentyOneRequestCode) {
    this.game.postTwentyOne({code: requestCode}).then(response => this.twentyOne = response);
  }

}
