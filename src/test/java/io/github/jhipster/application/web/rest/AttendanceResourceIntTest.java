package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LearningjhipsterApp;

import io.github.jhipster.application.domain.Attendance;
import io.github.jhipster.application.repository.AttendanceRepository;
import io.github.jhipster.application.service.dto.AttendanceDTO;
import io.github.jhipster.application.service.mapper.AttendanceMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AttendanceResource REST controller.
 *
 * @see AttendanceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearningjhipsterApp.class)
public class AttendanceResourceIntTest {

    private static final String DEFAULT_CHECK_IN_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_IN_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_OUT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_OUT_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SHIFT = "AAAAAAAAAA";
    private static final String UPDATED_SHIFT = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_VERIFY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VERIFY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SENSOR_ID = "AAAAAAAAAA";
    private static final String UPDATED_SENSOR_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_WORK_CODE = 1;
    private static final Integer UPDATED_WORK_CODE = 2;

    private static final String DEFAULT_SN = "AAAAAAAAAA";
    private static final String UPDATED_SN = "BBBBBBBBBB";

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAttendanceMockMvc;

    private Attendance attendance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttendanceResource attendanceResource = new AttendanceResource(attendanceRepository, attendanceMapper);
        this.restAttendanceMockMvc = MockMvcBuilders.standaloneSetup(attendanceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Attendance createEntity(EntityManager em) {
        Attendance attendance = new Attendance()
            .checkInTime(DEFAULT_CHECK_IN_TIME)
            .checkOutTime(DEFAULT_CHECK_OUT_TIME)
            .description(DEFAULT_DESCRIPTION)
            .shift(DEFAULT_SHIFT)
            .checkType(DEFAULT_CHECK_TYPE)
            .verifyCode(DEFAULT_VERIFY_CODE)
            .sensorId(DEFAULT_SENSOR_ID)
            .workCode(DEFAULT_WORK_CODE)
            .sn(DEFAULT_SN);
        return attendance;
    }

    @Before
    public void initTest() {
        attendance = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttendance() throws Exception {
        int databaseSizeBeforeCreate = attendanceRepository.findAll().size();

        // Create the Attendance
        AttendanceDTO attendanceDTO = attendanceMapper.toDto(attendance);
        restAttendanceMockMvc.perform(post("/api/attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendanceDTO)))
            .andExpect(status().isCreated());

        // Validate the Attendance in the database
        List<Attendance> attendanceList = attendanceRepository.findAll();
        assertThat(attendanceList).hasSize(databaseSizeBeforeCreate + 1);
        Attendance testAttendance = attendanceList.get(attendanceList.size() - 1);
        assertThat(testAttendance.getCheckInTime()).isEqualTo(DEFAULT_CHECK_IN_TIME);
        assertThat(testAttendance.getCheckOutTime()).isEqualTo(DEFAULT_CHECK_OUT_TIME);
        assertThat(testAttendance.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAttendance.getShift()).isEqualTo(DEFAULT_SHIFT);
        assertThat(testAttendance.getCheckType()).isEqualTo(DEFAULT_CHECK_TYPE);
        assertThat(testAttendance.getVerifyCode()).isEqualTo(DEFAULT_VERIFY_CODE);
        assertThat(testAttendance.getSensorId()).isEqualTo(DEFAULT_SENSOR_ID);
        assertThat(testAttendance.getWorkCode()).isEqualTo(DEFAULT_WORK_CODE);
        assertThat(testAttendance.getSn()).isEqualTo(DEFAULT_SN);
    }

    @Test
    @Transactional
    public void createAttendanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attendanceRepository.findAll().size();

        // Create the Attendance with an existing ID
        attendance.setId(1L);
        AttendanceDTO attendanceDTO = attendanceMapper.toDto(attendance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttendanceMockMvc.perform(post("/api/attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Attendance in the database
        List<Attendance> attendanceList = attendanceRepository.findAll();
        assertThat(attendanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAttendances() throws Exception {
        // Initialize the database
        attendanceRepository.saveAndFlush(attendance);

        // Get all the attendanceList
        restAttendanceMockMvc.perform(get("/api/attendances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attendance.getId().intValue())))
            .andExpect(jsonPath("$.[*].checkInTime").value(hasItem(DEFAULT_CHECK_IN_TIME.toString())))
            .andExpect(jsonPath("$.[*].checkOutTime").value(hasItem(DEFAULT_CHECK_OUT_TIME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].shift").value(hasItem(DEFAULT_SHIFT.toString())))
            .andExpect(jsonPath("$.[*].checkType").value(hasItem(DEFAULT_CHECK_TYPE.toString())))
            .andExpect(jsonPath("$.[*].verifyCode").value(hasItem(DEFAULT_VERIFY_CODE.toString())))
            .andExpect(jsonPath("$.[*].sensorId").value(hasItem(DEFAULT_SENSOR_ID.toString())))
            .andExpect(jsonPath("$.[*].workCode").value(hasItem(DEFAULT_WORK_CODE)))
            .andExpect(jsonPath("$.[*].sn").value(hasItem(DEFAULT_SN.toString())));
    }

    @Test
    @Transactional
    public void getAttendance() throws Exception {
        // Initialize the database
        attendanceRepository.saveAndFlush(attendance);

        // Get the attendance
        restAttendanceMockMvc.perform(get("/api/attendances/{id}", attendance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attendance.getId().intValue()))
            .andExpect(jsonPath("$.checkInTime").value(DEFAULT_CHECK_IN_TIME.toString()))
            .andExpect(jsonPath("$.checkOutTime").value(DEFAULT_CHECK_OUT_TIME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.shift").value(DEFAULT_SHIFT.toString()))
            .andExpect(jsonPath("$.checkType").value(DEFAULT_CHECK_TYPE.toString()))
            .andExpect(jsonPath("$.verifyCode").value(DEFAULT_VERIFY_CODE.toString()))
            .andExpect(jsonPath("$.sensorId").value(DEFAULT_SENSOR_ID.toString()))
            .andExpect(jsonPath("$.workCode").value(DEFAULT_WORK_CODE))
            .andExpect(jsonPath("$.sn").value(DEFAULT_SN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAttendance() throws Exception {
        // Get the attendance
        restAttendanceMockMvc.perform(get("/api/attendances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttendance() throws Exception {
        // Initialize the database
        attendanceRepository.saveAndFlush(attendance);
        int databaseSizeBeforeUpdate = attendanceRepository.findAll().size();

        // Update the attendance
        Attendance updatedAttendance = attendanceRepository.findOne(attendance.getId());
        // Disconnect from session so that the updates on updatedAttendance are not directly saved in db
        em.detach(updatedAttendance);
        updatedAttendance
            .checkInTime(UPDATED_CHECK_IN_TIME)
            .checkOutTime(UPDATED_CHECK_OUT_TIME)
            .description(UPDATED_DESCRIPTION)
            .shift(UPDATED_SHIFT)
            .checkType(UPDATED_CHECK_TYPE)
            .verifyCode(UPDATED_VERIFY_CODE)
            .sensorId(UPDATED_SENSOR_ID)
            .workCode(UPDATED_WORK_CODE)
            .sn(UPDATED_SN);
        AttendanceDTO attendanceDTO = attendanceMapper.toDto(updatedAttendance);

        restAttendanceMockMvc.perform(put("/api/attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendanceDTO)))
            .andExpect(status().isOk());

        // Validate the Attendance in the database
        List<Attendance> attendanceList = attendanceRepository.findAll();
        assertThat(attendanceList).hasSize(databaseSizeBeforeUpdate);
        Attendance testAttendance = attendanceList.get(attendanceList.size() - 1);
        assertThat(testAttendance.getCheckInTime()).isEqualTo(UPDATED_CHECK_IN_TIME);
        assertThat(testAttendance.getCheckOutTime()).isEqualTo(UPDATED_CHECK_OUT_TIME);
        assertThat(testAttendance.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAttendance.getShift()).isEqualTo(UPDATED_SHIFT);
        assertThat(testAttendance.getCheckType()).isEqualTo(UPDATED_CHECK_TYPE);
        assertThat(testAttendance.getVerifyCode()).isEqualTo(UPDATED_VERIFY_CODE);
        assertThat(testAttendance.getSensorId()).isEqualTo(UPDATED_SENSOR_ID);
        assertThat(testAttendance.getWorkCode()).isEqualTo(UPDATED_WORK_CODE);
        assertThat(testAttendance.getSn()).isEqualTo(UPDATED_SN);
    }

    @Test
    @Transactional
    public void updateNonExistingAttendance() throws Exception {
        int databaseSizeBeforeUpdate = attendanceRepository.findAll().size();

        // Create the Attendance
        AttendanceDTO attendanceDTO = attendanceMapper.toDto(attendance);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAttendanceMockMvc.perform(put("/api/attendances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attendanceDTO)))
            .andExpect(status().isCreated());

        // Validate the Attendance in the database
        List<Attendance> attendanceList = attendanceRepository.findAll();
        assertThat(attendanceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAttendance() throws Exception {
        // Initialize the database
        attendanceRepository.saveAndFlush(attendance);
        int databaseSizeBeforeDelete = attendanceRepository.findAll().size();

        // Get the attendance
        restAttendanceMockMvc.perform(delete("/api/attendances/{id}", attendance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Attendance> attendanceList = attendanceRepository.findAll();
        assertThat(attendanceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Attendance.class);
        Attendance attendance1 = new Attendance();
        attendance1.setId(1L);
        Attendance attendance2 = new Attendance();
        attendance2.setId(attendance1.getId());
        assertThat(attendance1).isEqualTo(attendance2);
        attendance2.setId(2L);
        assertThat(attendance1).isNotEqualTo(attendance2);
        attendance1.setId(null);
        assertThat(attendance1).isNotEqualTo(attendance2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttendanceDTO.class);
        AttendanceDTO attendanceDTO1 = new AttendanceDTO();
        attendanceDTO1.setId(1L);
        AttendanceDTO attendanceDTO2 = new AttendanceDTO();
        assertThat(attendanceDTO1).isNotEqualTo(attendanceDTO2);
        attendanceDTO2.setId(attendanceDTO1.getId());
        assertThat(attendanceDTO1).isEqualTo(attendanceDTO2);
        attendanceDTO2.setId(2L);
        assertThat(attendanceDTO1).isNotEqualTo(attendanceDTO2);
        attendanceDTO1.setId(null);
        assertThat(attendanceDTO1).isNotEqualTo(attendanceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(attendanceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(attendanceMapper.fromId(null)).isNull();
    }
}
