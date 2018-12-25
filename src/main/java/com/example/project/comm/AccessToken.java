package com.example.project.comm;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AccessToken implements Serializable {
	private String access_token;
	private int expires_in;
}
