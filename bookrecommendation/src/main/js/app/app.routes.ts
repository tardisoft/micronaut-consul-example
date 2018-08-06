import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";

export const ROUTES: Routes = [
    {path: '', component: AppComponent}
];

@NgModule({
    imports: [
        RouterModule.forRoot(ROUTES, {useHash: true})
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {
}
