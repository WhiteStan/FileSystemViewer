package com.epam.test.util;

import static com.ninja_squad.dbsetup.Operations.*;

import com.ninja_squad.dbsetup.operation.Operation;

public class CommonDatabaseOperations {
	public static final Operation DELETE_ALL = 
		deleteAllFrom("app_user_user_profile", "user_profile",
				"app_user");	
	public static final Operation INSERT_DATA = 
			sequenceOf(insertInto("app_user")
					.columns("id","password","username")
					.values(1, "pass","someUser")
					.build(),
					insertInto("user_profile")
					.columns("id","type")
					.values(1,"ADMIN")
					.values(2, "USER")
					.build(),
					insertInto("app_user_user_profile")
					.columns("user_id","user_profile_id")
					.values(1,2)
					.build());
}
