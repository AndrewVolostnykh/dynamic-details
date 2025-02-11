import {Component, Input} from '@angular/core';
import {Detail} from '../../model/detail';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatCard} from '@angular/material/card';
import {MatInput} from '@angular/material/input';

@Component({
  selector: 'app-detail',
  standalone: true,
  imports: [
    MatLabel,
    MatCard,
    MatFormField,
    MatInput
  ],
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.css'
})
export class DetailComponent {
  @Input() detail!: Detail;

}
