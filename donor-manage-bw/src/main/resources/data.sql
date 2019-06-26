DELETE
FROM donations;

DELETE
FROM donors;

INSERT INTO donors (donorid, daddress, demail, dname, dphone)
	VALUES (5, '99 Inglebert Lane', 'chicken@cheese.com', 'Sarah Long', '522-332-1233'),
           (6, 'Southwest Parkway', 'south@west.com', 'IBM Corp', '211-211-2211'),
           (7, '1st St Blvd', 'bank@1st.com', 'Baccorp', '1-801-111-1211');

INSERT INTO donations (donationid, amount, donationdate, note, donorid)
    VALUES (7, 1000, '2019-06-25T20:56:26.766+0000', 'Thanks for all you do', 5),
            (8, 500, '2019-06-23T20:56:26.766+0000', 'Best of luck', 6),
            (9, 15, '2019-06-25T10:56:26.766+0000', 'Go Cause', 6);

alter sequence hibernate_sequence restart with 20;