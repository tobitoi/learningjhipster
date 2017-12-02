import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { LearningjhipsterRegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { LearningjhipsterCountryMySuffixModule } from './country-my-suffix/country-my-suffix.module';
import { LearningjhipsterLocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { LearningjhipsterDepartmentMySuffixModule } from './department-my-suffix/department-my-suffix.module';
import { LearningjhipsterTaskMySuffixModule } from './task-my-suffix/task-my-suffix.module';
import { LearningjhipsterEmployeeMySuffixModule } from './employee-my-suffix/employee-my-suffix.module';
import { LearningjhipsterAttendanceMySuffixModule } from './attendance-my-suffix/attendance-my-suffix.module';
import { LearningjhipsterJobMySuffixModule } from './job-my-suffix/job-my-suffix.module';
import { LearningjhipsterJobHistoryMySuffixModule } from './job-history-my-suffix/job-history-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        LearningjhipsterRegionMySuffixModule,
        LearningjhipsterCountryMySuffixModule,
        LearningjhipsterLocationMySuffixModule,
        LearningjhipsterDepartmentMySuffixModule,
        LearningjhipsterTaskMySuffixModule,
        LearningjhipsterEmployeeMySuffixModule,
        LearningjhipsterAttendanceMySuffixModule,
        LearningjhipsterJobMySuffixModule,
        LearningjhipsterJobHistoryMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LearningjhipsterEntityModule {}
