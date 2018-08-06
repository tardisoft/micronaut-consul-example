import {Component, OnInit} from '@angular/core';
import {Book, BookService} from "./book.service";
import {Observable} from "rxjs";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

    loading: boolean = true;

    constructor(private bookService: BookService) {
    }

    books$: Observable<Book[]>;

    ngOnInit(): void {
        this.books$ = this.bookService.list();
    }
}
