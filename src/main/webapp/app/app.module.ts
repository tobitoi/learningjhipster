import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { LearningjhipsterSharedModule, UserRouteAccessService } from './shared';
import { LearningjhipsterAppRoutingModule} from './app-routing.module';
import { LearningjhipsterHomeModule } from './home/home.module';
import { LearningjhipsterAdminModule } from './admin/admin.module';
import { LearningjhipsterAccountModule } from './account/account.module';
import { LearningjhipsterEntityModule } from './entities/entity.module';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        LearningjhipsterAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        LearningjhipsterSharedModule,
        LearningjhipsterHomeModule,
        LearningjhipsterAdminModule,
        LearningjhipsterAccountModule,
        LearningjhipsterEntityModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class LearningjhipsterAppModule {}
