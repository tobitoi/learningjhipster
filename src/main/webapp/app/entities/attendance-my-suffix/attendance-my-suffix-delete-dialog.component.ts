import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AttendanceMySuffix } from './attendance-my-suffix.model';
import { AttendanceMySuffixPopupService } from './attendance-my-suffix-popup.service';
import { AttendanceMySuffixService } from './attendance-my-suffix.service';

@Component({
    selector: 'jhi-attendance-my-suffix-delete-dialog',
    templateUrl: './attendance-my-suffix-delete-dialog.component.html'
})
export class AttendanceMySuffixDeleteDialogComponent {

    attendance: AttendanceMySuffix;

    constructor(
        private attendanceService: AttendanceMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.attendanceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'attendanceListModification',
                content: 'Deleted an attendance'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-attendance-my-suffix-delete-popup',
    template: ''
})
export class AttendanceMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private attendancePopupService: AttendanceMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.attendancePopupService
                .open(AttendanceMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
