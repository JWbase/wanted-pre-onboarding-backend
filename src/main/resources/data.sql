INSERT INTO company (company_id, city, country, company_name)
VALUES (1, 'SEOUL', 'KOREA', '원티드랩');

INSERT INTO job_posting (job_posting_id, compensation, posting_position, posting_details,
                         technology_used, company_id)
VALUES (1, 1000000, '백엔드 주니어 개발자', '원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..', 'Python', 1);

INSERT INTO members (members_id, name)
VALUES (1, '이정우')