import {BrowserModule} from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {BookService} from "./book.service";
import {CommonModule} from "@angular/common";

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        CommonModule,
        HttpClientModule
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
