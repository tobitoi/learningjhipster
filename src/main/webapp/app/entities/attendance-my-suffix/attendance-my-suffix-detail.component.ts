import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AttendanceMySuffix } from './attendance-my-suffix.model';
import { AttendanceMySuffixService } from './attendance-my-suffix.service';

@Component({
    selector: 'jhi-attendance-my-suffix-detail',
    templateUrl: './attendance-my-suffix-detail.component.html'
})
export class AttendanceMySuffixDetailComponent implements OnInit, OnDestroy {

    attendance: AttendanceMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private attendanceService: AttendanceMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAttendances();
    }

    load(id) {
        this.attendanceService.find(id).subscribe((attendance) => {
            this.attendance = attendance;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAttendances() {
        this.eventSubscriber = this.eventManager.subscribe(
            'attendanceListModification',
            (response) => this.load(this.attendance.id)
        );
    }
}
