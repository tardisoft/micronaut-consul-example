import {BrowserModule} from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app.routes";
import {HttpClientModule} from "@angular/common/http";
import {BookService} from "./book.service";

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule
    ],
    providers: [
        BookService
    ],
    bootstrap: [
        AppComponent
    ],
    schemas: [NO_ERRORS_SCHEMA],
})
export class AppModule {
}
