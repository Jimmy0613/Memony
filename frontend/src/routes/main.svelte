<script lang="ts">
  interface User {
    id: number;
    nickname: string;
    connectYn: string;
    // 필요한 필드 추가
  }

  let users: User[] = [];
  let loading = false;
  let error: string | null = null;

  async function fetchUsers() {
    loading = true;
    error = null;
    try {
      const res = await fetch('http://localhost:5000/api/users');
      if (!res.ok) {
        throw new Error(`HTTP error! status: ${res.status}`);
      }
      users = await res.json();
    } catch (e: unknown) {
      if (e instanceof Error) {
        error = e.message;
      } else {
        error = String(e);
      }
    } finally {
      loading = false;
    }
  }

  // 페이지 로드 시 데이터 불러오기
  import { onMount } from 'svelte';
  onMount(() => {
    fetchUsers();
  });
</script>

<style>
  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 1rem;
  }
  th, td {
    border: 1px solid #ddd;
    padding: 0.75rem;
    text-align: left;
  }
  th {
    background-color: #f3f3f3;
  }
</style>

<h1>회원 목록</h1>

{#if loading}
  <p>로딩 중...</p>
{:else if error}
  <p style="color: red;">에러: {error}</p>
{:else}
  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>닉네임</th>
        <th>api 연동</th>
      </tr>
    </thead>
    <tbody>
    {#each users as user}
      <tr>
        <td>{user.id}</td>
        <td>{user.nickname}</td>
        <td>{user.connectYn}</td>
      </tr>
    {/each}
    </tbody>
  </table>
{/if}
