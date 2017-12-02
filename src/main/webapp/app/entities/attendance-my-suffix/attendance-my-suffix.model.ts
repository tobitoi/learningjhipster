import { BaseEntity } from './../../shared';

export class AttendanceMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public checkInTime?: string,
        public checkOutTime?: string,
        public description?: string,
        public shift?: string,
        public checkType?: string,
        public verifyCode?: string,
        public sensorId?: string,
        public workCode?: number,
        public sn?: string,
        public employeeId?: number,
    ) {
    }
}
