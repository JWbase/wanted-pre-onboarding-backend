INSERT INTO company (company_id, city, country, company_name) VALUES (1, 'SEOUL', 'KOREA', '사무실1');

INSERT INTO company (company_id, city, country, company_name) VALUES (2, 'BUSAN', 'KOREA', '사무실2');

INSERT INTO job_posting (job_posting_id, compensation, posting_position, posting_details,
                         technology_used, company_id)
VALUES (1, 1000000, '백엔드 주니어 개발자', '사무실1에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..', 'Python', 1);

INSERT INTO job_posting (job_posting_id, compensation, posting_position, posting_details,
                         technology_used, company_id)
VALUES (2, 1000000, '백엔드 주니어 개발자', '사무실2에서 프론트 엔드 주니어 개발자를 채용합니다. 자격요건은..', 'Java', 1);

INSERT INTO job_posting (job_posting_id, compensation, posting_position, posting_details,
                         technology_used, company_id)
VALUES (3, 1000000, '백엔드 주니어 개발자', '사무실2에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..', 'Go', 2);

INSERT INTO users (user_id, name)
VALUES (1, '이정우')