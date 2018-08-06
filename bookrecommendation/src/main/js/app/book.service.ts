import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";

export interface Book {
    name: string
    isbn: string
    stock: number
}

export interface BookResponse {
    content: Book
}

@Injectable({
    providedIn: "root"
})
export class BookService {

    constructor(
        private http: HttpClient
    ) {
    }

    list(): Observable<Book[]> {
        return this.http.get<Book[]>("/books").pipe(
            tap(results => {
                results.sort((x, y) => x.name < y.name ? -1 : 1)
            })
        );
    }
}