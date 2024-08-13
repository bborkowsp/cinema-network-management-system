import {NgModule} from "@angular/core";
import {RepertoryComponent} from "./components/repertory.component";
import {CommonModule} from "@angular/common";
import {MatInputModule} from "@angular/material/input";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {ReactiveFormsModule} from "@angular/forms";
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import {ScreeningComponent} from './components/screening/screening.component';
import {BuyTicketComponent} from "./components/buy-ticket/buy-ticket.component";
import {StepsContainerComponent} from "./components/buy-ticket/components/steps-container/steps-container.component";
import {SeatSelectionComponent} from "./components/buy-ticket/components/steps/seat-selection/seat-selection.component";
import {TicketTypesComponent} from "./components/buy-ticket/components/steps/ticket-types/ticket-types.component";
import {OrderFormComponent} from "./components/buy-ticket/components/steps/order-form/order-form.component";
import {MatStepperModule} from "@angular/material/stepper";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSelectModule} from "@angular/material/select";

@NgModule({
  declarations: [
    RepertoryComponent,
    ScreeningComponent,
    BuyTicketComponent,
    StepsContainerComponent,
    SeatSelectionComponent,
    TicketTypesComponent,
    OrderFormComponent,
  ],
  imports: [
    CommonModule,
    MatInputModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    MatListModule,
    MatButtonModule,
    MatStepperModule,
    MatProgressSpinnerModule,
    MatSelectModule
  ],
})
export class RepertoryModule {
}
