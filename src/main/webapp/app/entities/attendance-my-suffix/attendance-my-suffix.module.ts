import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LearningjhipsterSharedModule } from '../../shared';
import {
    AttendanceMySuffixService,
    AttendanceMySuffixPopupService,
    AttendanceMySuffixComponent,
    AttendanceMySuffixDetailComponent,
    AttendanceMySuffixDialogComponent,
    AttendanceMySuffixPopupComponent,
    AttendanceMySuffixDeletePopupComponent,
    AttendanceMySuffixDeleteDialogComponent,
    attendanceRoute,
    attendancePopupRoute,
} from './';

const ENTITY_STATES = [
    ...attendanceRoute,
    ...attendancePopupRoute,
];

@NgModule({
    imports: [
        LearningjhipsterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AttendanceMySuffixComponent,
        AttendanceMySuffixDetailComponent,
        AttendanceMySuffixDialogComponent,
        AttendanceMySuffixDeleteDialogComponent,
        AttendanceMySuffixPopupComponent,
        AttendanceMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        AttendanceMySuffixComponent,
        AttendanceMySuffixDialogComponent,
        AttendanceMySuffixPopupComponent,
        AttendanceMySuffixDeleteDialogComponent,
        AttendanceMySuffixDeletePopupComponent,
    ],
    providers: [
        AttendanceMySuffixService,
        AttendanceMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LearningjhipsterAttendanceMySuffixModule {}
