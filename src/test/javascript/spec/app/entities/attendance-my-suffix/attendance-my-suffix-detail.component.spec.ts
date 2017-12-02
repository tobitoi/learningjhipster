/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { LearningjhipsterTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AttendanceMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/attendance-my-suffix/attendance-my-suffix-detail.component';
import { AttendanceMySuffixService } from '../../../../../../main/webapp/app/entities/attendance-my-suffix/attendance-my-suffix.service';
import { AttendanceMySuffix } from '../../../../../../main/webapp/app/entities/attendance-my-suffix/attendance-my-suffix.model';

describe('Component Tests', () => {

    describe('AttendanceMySuffix Management Detail Component', () => {
        let comp: AttendanceMySuffixDetailComponent;
        let fixture: ComponentFixture<AttendanceMySuffixDetailComponent>;
        let service: AttendanceMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LearningjhipsterTestModule],
                declarations: [AttendanceMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AttendanceMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(AttendanceMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AttendanceMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttendanceMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AttendanceMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.attendance).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
