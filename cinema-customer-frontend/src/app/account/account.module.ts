import {NgModule} from "@angular/core";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {MatInputModule} from "@angular/material/input";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {ReactiveFormsModule} from "@angular/forms";
import {MatListModule} from "@angular/material/list";
import {MatButtonModule} from "@angular/material/button";
import {MatStepperModule} from "@angular/material/stepper";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSelectModule} from "@angular/material/select";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatDialogModule} from "@angular/material/dialog";
import {SharedModule} from "../_shared/shared.module";
import {AccountPageComponent} from './components/account-page/account-page.component';
import {MatTabsModule} from "@angular/material/tabs";
import {RouterLink} from "@angular/router";
import {MatCardModule} from "@angular/material/card";
import {MyProfileComponent} from './components/account-page/tabs/my-profile/my-profile.component';
import {TicketsComponent} from "./components/account-page/tabs/tickets/tickets.component";
import {
  TicketDetailsComponent
} from "./components/account-page/tabs/tickets/components/ticket-list/ticket-details.component";
import {QRCodeModule} from "angularx-qrcode";
import {
  ChangePasswordComponent
} from "./components/account-page/tabs/my-profile/components/change-password/change-password.component";
import {
  PersonalDataComponent
} from "./components/account-page/tabs/my-profile/components/personal-data/personal-data.component";

@NgModule({
  declarations: [
    AccountPageComponent,
    MyProfileComponent,
    TicketsComponent,
    TicketDetailsComponent,
    ChangePasswordComponent,
    PersonalDataComponent
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
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    NgOptimizedImage,
    MatIconModule,
    MatTooltipModule,
    MatDialogModule,
    SharedModule,
    MatTabsModule,
    RouterLink,
    MatCardModule,
    QRCodeModule
  ],
})
export class AccountModule {
}
