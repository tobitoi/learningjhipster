import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AttendanceMySuffixComponent } from './attendance-my-suffix.component';
import { AttendanceMySuffixDetailComponent } from './attendance-my-suffix-detail.component';
import { AttendanceMySuffixPopupComponent } from './attendance-my-suffix-dialog.component';
import { AttendanceMySuffixDeletePopupComponent } from './attendance-my-suffix-delete-dialog.component';

export const attendanceRoute: Routes = [
    {
        path: 'attendance-my-suffix',
        component: AttendanceMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attendances'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'attendance-my-suffix/:id',
        component: AttendanceMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attendances'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attendancePopupRoute: Routes = [
    {
        path: 'attendance-my-suffix-new',
        component: AttendanceMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attendances'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'attendance-my-suffix/:id/edit',
        component: AttendanceMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attendances'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'attendance-my-suffix/:id/delete',
        component: AttendanceMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attendances'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
