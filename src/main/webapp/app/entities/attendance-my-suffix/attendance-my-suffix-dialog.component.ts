import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AttendanceMySuffix } from './attendance-my-suffix.model';
import { AttendanceMySuffixPopupService } from './attendance-my-suffix-popup.service';
import { AttendanceMySuffixService } from './attendance-my-suffix.service';
import { EmployeeMySuffix, EmployeeMySuffixService } from '../employee';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-attendance-my-suffix-dialog',
    templateUrl: './attendance-my-suffix-dialog.component.html'
})
export class AttendanceMySuffixDialogComponent implements OnInit {

    attendance: AttendanceMySuffix;
    isSaving: boolean;

    employees: EmployeeMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private attendanceService: AttendanceMySuffixService,
        private employeeService: EmployeeMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.employeeService.query()
            .subscribe((res: ResponseWrapper) => { this.employees = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.attendance.id !== undefined) {
            this.subscribeToSaveResponse(
                this.attendanceService.update(this.attendance));
        } else {
            this.subscribeToSaveResponse(
                this.attendanceService.create(this.attendance));
        }
    }

    private subscribeToSaveResponse(result: Observable<AttendanceMySuffix>) {
        result.subscribe((res: AttendanceMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AttendanceMySuffix) {
        this.eventManager.broadcast({ name: 'attendanceListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackEmployeeById(index: number, item: EmployeeMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-attendance-my-suffix-popup',
    template: ''
})
export class AttendanceMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private attendancePopupService: AttendanceMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.attendancePopupService
                    .open(AttendanceMySuffixDialogComponent as Component, params['id']);
            } else {
                this.attendancePopupService
                    .open(AttendanceMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
