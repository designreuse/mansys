package com.wicky.mansys.util;

import com.cares.biz.entity.UserVO;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>Version: 1.0
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.algorithmName}")
    private static String algorithmName = "md5";
    @Value("${password.hashIterations}")
    private static int hashIterations = 2;

    public static void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        PasswordHelper.randomNumberGenerator = randomNumberGenerator;
    }

    public static void setAlgorithmName(String algorithmName) {
        PasswordHelper.algorithmName = algorithmName;
    }

    public static void setHashIterations(int hashIterations) {
        PasswordHelper.hashIterations = hashIterations;
    }

    public static void encryptPassword(UserVO user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
    
	public static void main(String[] args) {
		UserVO user = new UserVO();
		
		user.setUsername("admin");
		user.setPassword("admin");
		encryptPassword(user);
		System.out.println("update sys_user set password = '"+user.getPassword()+"' and salt = '"+user.getSalt()+"' where id = 1;");
		
		user.setUsername("wang");
		user.setPassword("wang");
		encryptPassword(user);
		System.out.println("update sys_user set password = '"+user.getPassword()+"' and salt = '"+user.getSalt()+"' where id = 11;");
		
		user.setUsername("yang");
		user.setPassword("yang");
		encryptPassword(user);
		System.out.println("update sys_user set password = '"+user.getPassword()+"' and salt = '"+user.getSalt()+"' where id = 12;");
	}
}
